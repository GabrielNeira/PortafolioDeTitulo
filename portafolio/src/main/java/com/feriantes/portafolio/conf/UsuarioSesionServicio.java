package com.feriantes.portafolio.conf;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feriantes.portafolio.dao.UsuarioDao;
import com.feriantes.portafolio.to.UsuarioTO;


@Service
public class UsuarioSesionServicio implements UserDetailsService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	PasswordEncoder PasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioTO user=null;
		System.out.println("Password encriptada 1 :" + PasswordEncoder.encode("admin123"));
		System.out.println("Password encriptada 2 :" + PasswordEncoder.encode("admin123456"));
        try {
            user = usuarioDao.obtenerUsuarioMail(username);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		if (user == null ) {
			throw new UsernameNotFoundException("User not found");
		}
		List<SimpleGrantedAuthority> authorities = null;

			authorities = Arrays.asList(new SimpleGrantedAuthority(Integer.toString(user.getTipo())));
			return new User(user.getEmail(), user.getPassword(), authorities);
	

	}

    
    
}
