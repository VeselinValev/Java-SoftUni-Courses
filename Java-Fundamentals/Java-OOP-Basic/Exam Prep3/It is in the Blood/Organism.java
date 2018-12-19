package Exam12Nov2017;

import java.util.ArrayList;
import java.util.List;

public class Organism {
    private String name;
    private List<Cluster> clusters;

    public Organism(String name) {
        this.setName(name);
        this.setClusters(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    protected List<Cluster> getClusters() {
        return clusters;
    }

    private void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    public void addCluster(Cluster cluster){
        this.clusters.add(cluster);
    }

    private int findCellCount(){
        int cellCount =0;
        for (Cluster cluster: clusters){
            cellCount += cluster.getCells().size();
        }
        return cellCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Organism - ").append(this.getName()).append("\n");
        sb.append("--Clusters: ").append(getClusters().size()).append("\n");
        sb.append("--Cells: ").append(findCellCount()).append("\n");
        for (Cluster cluster: clusters){
            sb.append(cluster.toString());
        }
        return sb.toString();
    }
}
