package com.niit.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.niit.bean.Administrator;
import com.niit.bean.Student;
import com.niit.bean.Teacher;
import com.niit.service.AdministratorService;
import com.niit.service.StudentService;
import com.niit.service.TeacherService;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm{

    @Resource
    private AdministratorService administratorService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    /**
     * Ϊ��ǰ��½���û������ɫ��Ȩ��
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String id = (String) principalCollection.getPrimaryPrincipal();
        System.out.println(id + "����Ȩ��ҳ��");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // �����û�����ѯ��ǰ�û�ӵ�еĽ�ɫ
        Set<String> roleNames = new HashSet<String>();
        // �����û�����ѯ��ǰ�û�Ȩ��
        Set<String> permissionNames = new HashSet<String>();
        
        Administrator administrator = administratorService.selectAdministratorById(id); //�����ݿ��ѯ�û���Ϣ
        if (administrator != null) {
            roleNames.add("admin");
            permissionNames.add("admin");
        }
        Teacher teacher = teacherService.selectTeacherById(id); //�����ݿ��ѯ�û���Ϣ
        if (teacher != null) {
            roleNames.add("teacher");
            permissionNames.add("teacher");
        }
        Student student = studentService.selectStudentById(id); //�����ݿ��ѯ�û���Ϣ
        if (student != null) {
            roleNames.add("student");
            permissionNames.add("student");
        }

        // ����ɫ�����ṩ��info
        authorizationInfo.setRoles(roleNames);
        // ��Ȩ�������ṩ��info
        authorizationInfo.setStringPermissions(permissionNames);

        return authorizationInfo;
    }

    /**
     * �����֤
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String id = (String) authenticationToken.getPrincipal(); //��ȡ�û���
        System.out.println(id + "��½");
        Administrator administrator = administratorService.selectAdministratorById(id); //�����ݿ��ѯ�û���Ϣ
        if (administrator != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                    administrator.getId(), administrator.getPassword(), getName());
            SecurityUtils.getSubject().getSession().setAttribute("id", administrator.getId()); //�ѵ�ǰ�û��浽session��
            SecurityUtils.getSubject().getSession().setAttribute("no", "0");
            return authcInfo;
        }
        Teacher teacher = teacherService.selectTeacherById(id); //�����ݿ��ѯ�û���Ϣ
        if (teacher != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                    teacher.getId(), teacher.getPassword(), getName());
            SecurityUtils.getSubject().getSession().setAttribute("id", teacher.getId()); //�ѵ�ǰ�û��浽session��
            SecurityUtils.getSubject().getSession().setAttribute("no", "1");
            return authcInfo;
        }
        Student student = studentService.selectStudentById(id); //�����ݿ��ѯ�û���Ϣ
        if (student != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                    student.getId(), student.getPassword(), getName());
            SecurityUtils.getSubject().getSession().setAttribute("id", student.getId()); //�ѵ�ǰ�û��浽session��
            SecurityUtils.getSubject().getSession().setAttribute("no", "2");
            return authcInfo;
        }
        return null;
    }
}
