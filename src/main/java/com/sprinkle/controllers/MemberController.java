package com.sprinkle.controllers;

import com.sprinkle.dao.MemberDAO;
import com.sprinkle.models.MemberModel;
import java.util.List;

public class MemberController {
    private MemberDAO memberDAO;

    public MemberController() {
        memberDAO = new MemberDAO();
    }

    public List<MemberModel> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    public List<MemberModel> searchMembers(String keyword) {
        return memberDAO.searchMembers(keyword);
    }
    
    public boolean add(MemberModel member) {
        return memberDAO.add(member);
    }
//    
    public boolean updateMember(MemberModel member) {
        return memberDAO.update(member);
    }
    
    public boolean delete(int memberId) {
        return memberDAO.delete(memberId);
    }
}
