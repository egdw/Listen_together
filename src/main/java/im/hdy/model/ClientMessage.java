package im.hdy.model;

/**
 * Created by hdy on 19/02/2018.
 */
public class ClientMessage {
    private Integer type;
    private String id;
    private double time;
    private boolean canplay;
    private Integer action;
    private Integer index;
    private Long timestamp;

    public ClientMessage(Integer type, String id, double time, boolean canplay, Integer action, Integer index,Long timestamp) {
        this.type = type;
        this.id = id;
        this.time = time;
        this.canplay = canplay;
        this.action = action;
        this.index = index;
        this.timestamp = timestamp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public boolean isCanplay() {
        return canplay;
    }

    public void setCanplay(boolean canplay) {
        this.canplay = canplay;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ClientMessage{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", time=" + time +
                ", canplay=" + canplay +
                ", action=" + action +
                ", index=" + index +
                ", timestamp=" + timestamp +
                '}';
    }
}
