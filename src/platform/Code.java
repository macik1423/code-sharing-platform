package platform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Code implements Comparable<Code> {
    @Id
    @JsonIgnore
    @Type(type = "uuid-char")
    @Column(columnDefinition = "uniqueidentifier")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @JsonIgnore
    public static long counter;

    @JsonIgnore
    private long extraId;
    private String code;
    private LocalDateTime date;
    private long views;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long baseTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long baseViews;
    private long time; //expiredTime

    public Code(String code, LocalDateTime date, long view, long time) {
        this.code = code;
        this.date = date;
        this.views = view;
        this.baseTime = time;
        this.time = time;
        this.baseViews = view;
    }

    public Code() {

    }

    public void setExtraId(long extraId) {
        this.extraId = extraId;
    }

    public long getBaseViews() {
        return baseViews;
    }

    public String getCode() {
        return code;
    }

    public long getViews() {
        return views;
    }

    public long getTime() {
        return time;
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(long baseTime) {
        this.baseTime = baseTime;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getExtraId() {
        return extraId;
    }

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public int compareTo(Code code) {
        int dateCmp = code.getDate().compareTo(date);
        if (dateCmp != 0) {
            return dateCmp;
        }
        return Long.compare(code.getExtraId(), extraId);
    }


    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                ", date=" + date +
                ", views=" + views +
                ", baseTime=" + baseTime +
                ", baseViews=" + baseViews +
                ", time=" + time +
                '}';
    }
}
