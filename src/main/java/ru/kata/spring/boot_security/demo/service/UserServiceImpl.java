//package ru.kata.spring.boot_security.demo.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.dao.Dao;
//import ru.kata.spring.boot_security.demo.model.User;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UsersService {
//
//    Dao userDAO;
//    @Autowired
//    public UserServiceImpl(Dao userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public User getUserBiId(int id) {
//        return userDAO.getUserBiId(id);
//    }
//    @Override
//    @Transactional
//    public void saveUser(User user) {
//        userDAO.saveUser(user);
//    }
//    @Override
//    @Transactional
//    public void updateUser(User user) {
//        userDAO.updateUser(user);
//    }
//    @Override
//    @Transactional
//    public void removeUserBiId(int id) {
//        userDAO.removeUserBiId(id);
//    }
//    @Override
//    @Transactional(readOnly = true)
//    public List<User> getAllUsers() {
//        return userDAO.getAllUsers();
//    }
//
///*    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("Пользователь с именем '%s' не найден", username));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
//    }*/
//}
