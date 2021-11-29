package kr.co.team.res.config.security.direct;


import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.MemberRoll;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Data
@EqualsAndHashCode(callSuper=false)
public class UserDetails extends User
{
    private static final long serialVersionUID = 1L;

    @Getter
    private Account account;

    public UserDetails(Account account)
    {

        super
                (
                        account.getLoginId(),
                        account.getPwd(),
                        getGranted(account.getAuthorites())
                );

        this.account = account;
    }

    private static Collection<? extends GrantedAuthority> getGranted(String roll) {
        //GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        /*for (UserRoll roll: userRolls) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roll.getUserRollType().toString().toUpperCase()));
        }*/
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roll));
        return grantedAuthorities;
    }

    private static Collection<? extends GrantedAuthority> getGranted(List<MemberRoll> authorities) {
        //GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (MemberRoll authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getMberDvTy().name()));
        }
        return grantedAuthorities;
    }
}