package blog02.demo.pojo;

import java.util.Date;
import java.util.List;

public class Blog {

    private Long id;
    private Boolean appreciation;
    private Boolean commentabled;
    private Date createTime;
    private String description;
    private String firstPicture;
    private String flag;
    private Boolean published;
    private Boolean recommend;
    private Boolean shareStatement;
    private String title;
    private Date updateTime;
    private Integer views;
    private Long typeId;
    private Long userId;
    private String content;

    private String tlagTable;
    private User user;
    private Type type;
    private List<Tlag> tlags;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", appreciation=" + appreciation +
                ", commentabled=" + commentabled +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", published=" + published +
                ", recommend=" + recommend +
                ", shareStatement=" + shareStatement +
                ", title='" + title + '\'' +
                ", updateTime=" + updateTime +
                ", views=" + views +
                ", typeId=" + typeId +
                ", userId=" + userId +
                ", user=" + user +
                ", type=" + type +
                ", tlags=" + tlags +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void ObjToStringTlagTable(){
        this.tlagTable = "";
        for (Tlag tlag:this.tlags){
            this.tlagTable += "" + tlag.getId() + ",";
        }
    }

    public void NullToFalse(){
        if (this.getAppreciation()==null){
            this.setAppreciation(false);
        }
        if (this.getCommentabled()==null){
            this.setCommentabled(false);
        }
        if (this.getRecommend()==null){
            this.setRecommend(false);
        }
        if (this.getShareStatement()==null){
            this.setShareStatement(false);
        }
    }

    public String getTlagTable() {
        return tlagTable;
    }

    public void setTlagTable(String tlagTable) {
        this.tlagTable = tlagTable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public Boolean getCommentabled() {
        return commentabled;
    }

    public void setCommentabled(Boolean commentabled) {
        this.commentabled = commentabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Boolean getShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(Boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tlag> getTlags() {
        return tlags;
    }

    public void setTlags(List<Tlag> tlags) {
        this.tlags = tlags;
    }
}
