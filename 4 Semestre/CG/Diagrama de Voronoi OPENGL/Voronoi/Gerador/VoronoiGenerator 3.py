from collections import defaultdict

from shapely.geometry import Polygon

import numpy as np

def voronoi_polygons(voronoi, diameter):
    """Generate shapely.geometry.Polygon objects corresponding to the
    regions of a scipy.spatial.Voronoi object, in the order of the
    input points. The polygons for the infinite regions are large
    enough that all points within a distance 'diameter' of a Voronoi
    vertex are contained in one of the infinite polygons.

    """
    centroid = voronoi.points.mean(axis=0)

    # Mapping from (input point index, Voronoi point index) to list of
    # unit vectors in the directions of the infinite ridges starting
    # at the Voronoi point and neighbouring the input point.
    ridge_direction = defaultdict(list)
    for (p, q), rv in zip(voronoi.ridge_points, voronoi.ridge_vertices):
        u, v = sorted(rv)
        if u == -1:
            # Infinite ridge starting at ridge point with index v,
            # equidistant from input points with indexes p and q.
            t = voronoi.points[q] - voronoi.points[p] # tangent
            n = np.array([-t[1], t[0]]) / np.linalg.norm(t) # normal
            midpoint = voronoi.points[[p, q]].mean(axis=0)
            direction = np.sign(np.dot(midpoint - centroid, n)) * n
            ridge_direction[p, v].append(direction)
            ridge_direction[q, v].append(direction)

    for i, r in enumerate(voronoi.point_region):
        region = voronoi.regions[r]
        if -1 not in region:
            # Finite region.
            yield Polygon(voronoi.vertices[region])
            continue
        # Infinite region.
        inf = region.index(-1)              # Index of vertex at infinity.
        j = region[(inf - 1) % len(region)] # Index of previous vertex.
        k = region[(inf + 1) % len(region)] # Index of next vertex.
        if j == k:
            # Region has one Voronoi vertex with two ridges.
            dir_j, dir_k = ridge_direction[i, j]
        else:
            # Region has two Voronoi vertices, each with one ridge.
            dir_j, = ridge_direction[i, j]
            dir_k, = ridge_direction[i, k]

        # Length of ridges needed for the extra edge to lie at least
        # 'diameter' away from all Voronoi vertices.
        length = 2 * diameter / np.linalg.norm(dir_j + dir_k)

        # Polygon consists of finite part plus an extra edge.
        finite_part = voronoi.vertices[region[inf + 1:] + region[:inf]]
        extra_edge = [voronoi.vertices[j] + dir_j * length,
                      voronoi.vertices[k] + dir_k * length]
        yield Polygon(np.concatenate((finite_part, extra_edge)))


def read_points_from_file(filename):
    points = []
    lines = open(filename).readlines()
    lines = lines[1:]
    for line in lines:
            x, y = map(float, line.strip().split())
            points.append([x, y])
    return np.array(points)

import matplotlib.pyplot as plt
from scipy.spatial import Voronoi

points = read_points_from_file("PoligonoDeTeste.txt")
x_min, y_min = np.min(points, axis=0)
x_max, y_max = np.max(points, axis=0)
width = (x_max - x_min) * 0.1
height = (y_max - y_min) * 0.1
x_min = x_min - width
x_max = x_max + width
y_min = y_min - height
y_max = y_max + height

boundary = np.array([[x_min, y_min], [x_max, y_min], [x_max, y_max], [x_min, y_max]])

x, y = boundary.T
plt.xlim(round(x.min() - 1), round(x.max() + 1))
plt.ylim(round(y.min() - 1), round(y.max() + 1))
plt.plot(*points.T, 'b.')

diameter = np.linalg.norm(boundary.ptp(axis=0))
boundary_polygon = Polygon(boundary)
with open('polygon_coordinates.txt', 'w') as f:
    polygons = list(voronoi_polygons(Voronoi(points), diameter))
    f.write(f"{len(polygons)}\n")
    for i, p in enumerate(polygons):
        intersection = p.intersection(boundary_polygon)
        if not intersection.is_empty:
            x, y = zip(*intersection.exterior.coords) 
            plt.plot(x, y, 'r-')
            label = f"P{i+1}"
            plt.text(np.mean(x), np.mean(y), label, ha='center', va='center', fontsize=8)
            x = x[:-1]# Exclude the first vertex
            y = y[:-1]# Exclude the first vertex
            num_vertex = len(x)
            coords_str = ' '.join([f"{x_val:.2f} {y_val:.2f}" for x_val, y_val in zip(x, y)])
            f.write(f"{num_vertex} {coords_str}\n")


plt.show()