package com.codeup.capstone.models;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Entity
@Table(name="user")
public class User {

    //   ------------------Instance variables-------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100, unique = true)
    private String userName;

    @Pattern(regexp = "([a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+)", message = "email must be" +
            " valid email address")
    @Column(nullable = false, unique = true)
    private String email;


    //one upper case, one lower case, one digit, one special character, minimum 8 characters in length
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[0-9]).{8,}$", message = "Password length must be at least 8 characters " +
            "with one uppercase letter and one digit")
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 100)
    private String pronouns;

    @Column(nullable = false, length = 100)
    private Date birthDate;

    @Column(length = 500)
    private String bio;

    @Column(length = 100)
    @ColumnDefault("true")
    private Boolean isSiteAdmin;

    @Column(length = 100)
    private Boolean isBanned;

    @Column(columnDefinition = "TEXT")
    private String profilePic;

    @Column(length = 500)
    private String twitchInfo;

    @Column(length = 500)
    private String steamInfo;

    @Column(length = 500)
    private String xboxLiveInfo;

    @Column(length = 500)
    private String psnInfo;

    @Column(length = 500)
    private String nintenDoInfo;

    @Column(length = 500)
    private String discordInfo;


//    ------------constructors----------------------------

    public User() {
    }

    //   ------------------------- with parameters---------------
    public User(long id, String userName, String email, String password, String fullName,
                String pronouns, Date birthDate, String bio, Boolean isSiteAdmin, Boolean isBanned, String profilePic,
                String twitchInfo, String steamInfo, String xboxLiveInfo, String psnInfo, String nintenDoInfo,
                String discordInfo, User user) {
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

    // implement the Copy Constructor right here in the User model!
    // We can call on this constructor from elsewhere in our code, and don't have to specify all of
    // the User object's properties (like email, username, etc)
    public User(User copy) {
        this.id = copy.id; // VERY IMPORTANT. Many things won't work if you don't include this assignment
        this.email = copy.email;
        this.userName = copy.userName;
        this.password = copy.password;
        this.fullName = copy.fullName;
        this.pronouns = copy.pronouns;
        this.birthDate = copy.birthDate;
        this.bio = copy.bio;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDiscordInfo(String discordInfo) {
        this.discordInfo = discordInfo;
    }

}

