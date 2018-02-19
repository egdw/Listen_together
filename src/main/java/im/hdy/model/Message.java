package im.hdy.model;

/**
 * Created by hdy on 17/02/2018.
 */
public class Message {
    //信息类型
    //0 房员请求同步信息
    //1 请求上传播放进度~
    //2 房主返回上传播放进度和状态
    //3
    private Integer type;
    //判断是否是房主
    private boolean isfirst;
    //当前播放index
    private Integer index;
    //当前播放进度
    private double time;
    //当前播放状态
    //0 播放
    //1 暂停
    //2 缓存完成
    private Integer status;
    private Long timestamp;

    public Message(Integer type, boolean isfirst, Integer index, double time, Integer status,Long timestamp) {
        this.type = type;
        this.isfirst = isfirst;
        this.index = index;
        this.time = time;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isIsfirst() {
        return isfirst;
    }

    public void setIsfirst(boolean isfirst) {
        this.isfirst = isfirst;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", isfirst=" + isfirst +
                ", index=" + index +
                ", time=" + time +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
