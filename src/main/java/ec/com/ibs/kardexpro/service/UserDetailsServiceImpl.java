package ec.com.ibs.kardexpro.service;

import com.querydsl.core.QueryException;
import ec.com.ibs.kardexpro.model.UsuarioEntity;
import ec.com.ibs.kardexpro.model.UsuarioRolEntity;
import ec.com.ibs.kardexpro.model.vo.UserDetailVO;
import ec.com.ibs.kardexpro.repository.ILoginUsuarioRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private UsuarioAutenticacionDao usuarioAutenticacionDao;
    @Autowired
    private ILoginUsuarioRepository loginUsuarioRepositroy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UsuarioEntity user = loginUsuarioRepositroy.obtenerUsuarioPorNombre(username);
            if (user == null) {
                throw new UsernameNotFoundException(String.format("No existe el usuario '%s'.", username));
            }
            user.setRolesUsuario(obtenerRolesUsuario(user.getIdUsuario()));
            return create(user);
        } catch (HibernateException e) {
//            throw new KardexExcepction("No fue posible validar el usuario.", e);
        	throw e;
        }
    }

    public List<UsuarioRolEntity> obtenerRolesUsuario(Integer userId) {
        try {
            if (userId == null) {
                //throw new KardexExcepction("El codigo de usuario es obligatorio para obtener los roles");
            }
            return loginUsuarioRepositroy.obtenerRolesUsuario(userId);
        } catch (QueryException e) {
            //throw new KardexExcepction("No fue posible ontener los roles asociado al asuario", e);
        }
        return null;
    }

    private UserDetailVO create(UsuarioEntity user) {
        UserDetailVO userDetail = new UserDetailVO();
        userDetail.setUsername(user.getNombreUsuario());
        userDetail.setPassword(user.getClave());
        userDetail.setEstado(user.isEstado());
        userDetail.setRolesUsuario(mapToRolesUsuario(user.getRolesUsuario()));
        return userDetail;
    }

    private List<GrantedAuthority> mapToRolesUsuario(List<UsuarioRolEntity> rolesUsuario) {
        return rolesUsuario.stream().map(role -> new SimpleGrantedAuthority(role.getRol().getNombreRol()))
                .collect(Collectors.toList());
    }

}
