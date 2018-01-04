package com.lingyun.framework.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "mid")
    private Message messageID;

    private String  ccontent;

    private Date mDate;

    public Comment() {
        super();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", messageID=" + messageID +
                ", ccontent='" + ccontent + '\'' +
                ", mDate=" + mDate +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Message getMessageID() {
        return messageID;
    }

    public void setMessageID(Message messageID) {
        this.messageID = messageID;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
