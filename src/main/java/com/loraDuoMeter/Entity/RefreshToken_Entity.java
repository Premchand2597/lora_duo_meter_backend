package com.loraDuoMeter.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "refresh_token_table")
public class RefreshToken_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false, updatable = false)
    private String token;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime expiresAt;
    
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private boolean revoked;

    private String replacedByToken;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public String getReplacedByToken() {
		return replacedByToken;
	}

	public void setReplacedByToken(String replacedByToken) {
		this.replacedByToken = replacedByToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "RefreshToken_Entity [id=" + id + ", token=" + token + ", createdAt=" + createdAt + ", expiresAt="
				+ expiresAt + ", username=" + username + ", revoked=" + revoked + ", replacedByToken=" + replacedByToken
				+ "]";
	}

	public RefreshToken_Entity(String token, LocalDateTime createdAt, LocalDateTime expiresAt, String username,
			boolean revoked, String replacedByToken) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.username = username;
		this.revoked = revoked;
		this.replacedByToken = replacedByToken;
	}

	public RefreshToken_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
