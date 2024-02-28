package tn.esprit.project.models;

public class Participation {
    private int id_participation;
    private int id_event;
    private int id_planning;
    private String status;

    public Participation(int id_participation, int id_event, int id_planning, String status) {
        this.id_participation = id_participation;
        this.id_event = id_event;
        this.id_planning = id_planning;
        this.status = status;
    }

    public Participation(int id_event, int id_planning, String status) {
        this.id_event = id_event;
        this.id_planning = id_planning;
        this.status = status;
    }

    public Participation() {
    }

    public int getId_participation() {
        return id_participation;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_planning() {
        return id_planning;
    }

    public void setId_planning(int id_planning) {
        this.id_planning = id_planning;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id_participation=" + id_participation +
                ", id_event=" + id_event +
                ", id_planning=" + id_planning +
                ", status='" + status + '\'' +
                '}';
    }
}
