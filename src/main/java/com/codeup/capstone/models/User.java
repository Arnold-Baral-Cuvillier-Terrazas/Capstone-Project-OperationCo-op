package com.codeup.capstone.models;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="user")
public class User {

//   ------------------Instance variables-------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String userName;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 100)
    private String pronouns;

    @Column(nullable = false, length = 100)
    private Date birthDate;

    @Column(nullable = false, length = 500)
    private String bio;

    @Column(nullable = false, length = 100)
    private Boolean isSiteAdmin;

    @Column(nullable = false, length = 100)
    private Boolean isBanned;

    @Column(nullable = false, length = 500)
    private String profilePic;

    @Column(nullable = false, length = 500)
    private String twitchInfo;

    @Column(nullable = false, length = 500)
    private String steamInfo;

    @Column(nullable = false, length = 500)
    private String xboxLiveInfo;

    @Column(nullable = false, length = 500)
    private String psnInfo;

    @Column(nullable = false, length = 500)
    private String nintenDoInfo;

    @Column(nullable = false, length = 500)
    private String discordInfo;

    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Post> posts;

//    ------------constructors----------------------------

    public User() {}

//   ------------------------- with parameters---------------
    public User(long id, String userName, String email, String password, String fullName,
                String pronouns, Date birthDate, String bio, Boolean isSiteAdmin, Boolean isBanned, String profilePic,
                String twitchInfo, String steamInfo, String xboxLiveInfo, String psnInfo, String nintenDoInfo,
                String discordInfo) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.pronouns = pronouns;
        this.birthDate = birthDate;
        this.bio = bio;
        this.isSiteAdmin = isSiteAdmin;
        this.isBanned = isBanned;
        this.profilePic = profilePic;
        this.twitchInfo = twitchInfo;
        this.steamInfo = steamInfo;
        this.xboxLiveInfo = xboxLiveInfo;
        this.psnInfo = psnInfo;
        this.nintenDoInfo = nintenDoInfo;
        this.discordInfo = discordInfo;
    }

//  ------------ getters and setters-------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName(String fullName) {
        return fullName;
    }

    public void setFirstName(String fullName) {
        this.fullName = fullName;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Boolean getSiteAdmin() {
        return isSiteAdmin;
    }

    public void setSiteAdmin(Boolean siteAdmin) {
        isSiteAdmin = siteAdmin;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getTwitchInfo() {
        return twitchInfo;
    }

    public void setTwitchInfo(String twitchInfo) {
        this.twitchInfo = twitchInfo;
    }

    public String getSteamInfo() {
        return steamInfo;
    }

    public void setSteamInfo(String steamInfo) {
        this.steamInfo = steamInfo;
    }

    public String getXboxLiveInfo() {
        return xboxLiveInfo;
    }

    public void setXboxLiveInfo(String xboxLiveInfo) {
        this.xboxLiveInfo = xboxLiveInfo;
    }

    public String getPsnInfo() {
        return psnInfo;
    }

    public void setPsnInfo(String psnInfo) {
        this.psnInfo = psnInfo;
    }

    public String getNintenDoInfo() {
        return nintenDoInfo;
    }

    public void setNintenDoInfo(String nintenDoInfo) {
        this.nintenDoInfo = nintenDoInfo;
    }

    public String getDiscordInfo() {
        return discordInfo;
    }

    public void setDiscordInfo(String discordInfo) {
        this.discordInfo = discordInfo;
    }
}

