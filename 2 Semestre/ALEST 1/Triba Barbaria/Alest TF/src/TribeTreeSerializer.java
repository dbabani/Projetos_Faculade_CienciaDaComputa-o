import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TribeTreeSerializer {
    private int firstWarriorLands;
    private ArrayList<LineSerializer> lineSerializers;

    public TribeTreeSerializer(int firstWarriorLands) {
        this.firstWarriorLands = firstWarriorLands;
        this.lineSerializers = new ArrayList<LineSerializer>();
    }
    
    public class LineSerializer {
        private String fatherName;
        private String childName;
        private int childLands;

        public LineSerializer(
            String fatherName, 
            String childName, 
            int childLands
        ) {
            this.fatherName = fatherName;
            this.childName = childName;
            this.childLands = childLands;
        }

        public String getFatherName() {
            return this.fatherName;
        }

        public String getChildName() {
            return this.childName;
        }

        public int getChildlands() {
            return this.childLands;
        }
    }

    public void addLineSerializer(
        String fatherName, 
        String childName, 
        int childLands
    ) {
        lineSerializers.add(new LineSerializer(
            fatherName, 
            childName, 
            childLands
        ));
    }

    public String getFirstWarrior() {
        return this.lineSerializers.stream().filter(ls1 -> 
            this.lineSerializers.stream()
                .filter(ls2 -> ls2.childName.equals(ls1.fatherName))
                .count() == 0
        ).findFirst().get().fatherName;  
    }

    private List<LineSerializer> getWarriorLineSerializers(
        String fatherName
    ) {
        return this.lineSerializers.stream()
            .filter(ls -> ls.fatherName.equals(fatherName))
            .collect(Collectors.toList()); 
    }

    public ArrayList<LineSerializer> getLineSerializers() {
        return this.lineSerializers;
    }

    public TribeTree generateTribeTree() {
        TribeTree tribeTree = new TribeTree(
            this.getFirstWarrior(),
            this.firstWarriorLands
        );

        insertWarriorHelper(
            tribeTree, 
            tribeTree.getFirstWarrior().getWarriorName()
        );

        return tribeTree;
    }

    private void insertWarriorHelper(TribeTree tribeTree, String fatherName) {
        getWarriorLineSerializers(fatherName).forEach(ls -> {
            tribeTree.insert(fatherName, ls.childName, ls.childLands);

            insertWarriorHelper(tribeTree, ls.childName);
        });
    }
}
