package fa.group1.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fa.group1.entities.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;
    private GrantedAuthority authority;
    public UserDetailsImpl(Integer id, String username,  String password,
                           GrantedAuthority authority) {
        this.id = id;
        this.username = username;

        this.password = password;
        this.authority = authority;
    }

    public static UserDetailsImpl build(User account) {
        GrantedAuthority authority= new SimpleGrantedAuthority(account.getRole().getRoleName());

        return new UserDetailsImpl(
                account.getAccountId(),
                account.getUsername(),
                account.getPassword(),
                authority);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    public GrantedAuthority getAuthoritity(){
        return authority;
    }
    @Override
    public String getPassword() {
        return password;
    }
    public Integer getId(){
        return id;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
