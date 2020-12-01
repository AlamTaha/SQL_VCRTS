public class TaskOwner {
    private int clientID;
    private int jobDuration;
    private String jobDeadline;

    public TaskOwner(int clientID, int jobDuration, String jobDeadline) {
        this.clientID = clientID;
        this.jobDuration = jobDuration;
        this.jobDeadline = jobDeadline;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int newClientID) {
        this.clientID = newClientID;
    }

    public int getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(int newJobDuration) {
        this.jobDuration = newJobDuration;
    }

    public String getJobDeadline() {
        return jobDeadline;
    }

    public void setJobDeadline(String newJobDeadline) {
        this.jobDeadline = newJobDeadline;
    }
}