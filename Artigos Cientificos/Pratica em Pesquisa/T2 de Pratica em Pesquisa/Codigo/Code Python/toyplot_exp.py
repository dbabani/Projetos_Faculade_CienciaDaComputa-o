import toyplot as tp
import numpy as np
import networkit as nk
import os

def str_list_to_int(str_list):
  return [int(item) for item in str_list]

def read_edges_from_file(filename):
  with open(filename, "r") as f:
    lines = f.readlines()
    edges = [str_list_to_int(line.split()) for line in lines]
  return edges 

def get_nk_communities():
  nk_graph = nk.graph.Graph()
  edges = []

  for edge in read_edges_from_file(os.path.dirname(__file__) + '/road-euroroad/road-euroroad.edges'): 
    nk_graph.addEdge(edge[0], edge[1], 0, True)
    edges.append([edge[0], edge[1]])

  communities = nk.community.detectCommunities(nk_graph, nk.community.PLP(nk_graph))
  
  communities_keys = []

  for community in communities.getVector():
    if community not in communities_keys and community != 0:
      communities_keys.append(community)
    
  truth = [[]] * (nk_graph.numberOfNodes() - 1)

  for index, community in enumerate(communities_keys):
    for member in communities.getMembers(community):
      truth[member - 1] = [member, index + 1]

  return (edges, truth)

edges, truth = get_nk_communities()

colormap = tp.color.brewer.map("Set1")
ecolor = "lightgray"
vlstyle = {"fill":"white"}
vcolor = np.array(truth)[:,1]

graph = tp.graph(np.array(edges), ecolor=ecolor, vsize=20, vlstyle=vlstyle, vcolor=(vcolor, colormap))