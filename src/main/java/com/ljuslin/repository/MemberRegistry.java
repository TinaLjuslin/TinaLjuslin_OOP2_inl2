package com.ljuslin.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.Member;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Holds all members of this rental shop
 * @author Tina Ljuslin
 */
public class MemberRegistry {
    private final String FILENAME = "members.json";
    private ObjectMapper mapper = new ObjectMapper();
    private final File memberFile = new File(FILENAME);

    public MemberRegistry() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (!memberFile.exists()) {
            try {
                mapper.writeValue(memberFile, new ArrayList<Member>());
            } catch (IOException e) {
                System.err.println("VARNING: Kunde inte skapa initial medlemsfil.");
            }
        }
    }
    private void saveMembers(List<Member> members) throws FileException {
        try {
            mapper.writeValue(memberFile, members);
        } catch (IOException e) {
            throw new FileException("Kunde ej spara medlemmar till fil");
        }
    }

    public Member getMember(String id) throws FileException, MemberException {
        List<Member> members;
        try {
            members = getMembers();
            for (Member member : members) {
                if (member.getMemberID().equals(id)) {
                    return member;
                }
            }
        } catch (Exception e) {
            throw new FileException("Medlemsfilen kunde ej läsas");
        }
        throw new MemberException("Medlem med id " + id + " kunde ej hittas");
    }

    public void changeMember(Member member) throws FileException {
        List<Member> members;
        try {
            members = getMembers();
        } catch (FileException ex) {
            throw new FileException("Medlemsfilen kunde inte läsas");
        }
        for (Member m : members) {
            if (m.getMemberID().equals(member.getMemberID())) {
                m.setFirstName(member.getFirstName());
                m.setLastName(member.getLastName());
                m.setMemberLevel(member.getMemberLevel());
            }
        }
        saveMembers(members);
    }

    public void removeMember(Member member) throws FileException, MemberException {
        List<Member> members;
        try {
            members = getMembers();
        } catch (FileException ex) {
            throw new FileException("Medlemsfilen kunde inte läsas");
        }
        boolean removed = false;
        for (Member m : members) {//exception för att den sista posten är borttagen
            if (m.getMemberID().equals(member.getMemberID())) {
                members.remove(m);
                removed = true;
                break;
            }
        }
        if (!removed) {
            throw new MemberException("Member could not be found!");
        }
        saveMembers(members);
    }

    public List<Member> getMembers() throws FileException {
        try {
            return new ArrayList<>(Arrays.asList(mapper.readValue(memberFile,
                    Member[].class)));

        } catch (Exception e) {
            throw new FileException("Medlemsfilen kunde ej läsas");
        }
    }
    public void addMember(Member member) throws FileException {
        try {
            List<Member> members = getMembers();
            members.add(member);
            mapper.writeValue(new File(FILENAME), members);
        } catch (Exception e) {
            throw new FileException("Den nya medlemmen kunde ej sparas");
        }
    }
    public void addToHistory(Member member, String history) throws FileException, MemberException {
        List<Member> members;
        try {
            members = getMembers();
        } catch (FileException ex) {
            throw new FileException("Medlemsfilen kunde inte läsas");
        }
        for (Member m : members) {
            //kolla om medlemen inte kunde hittas, throw MemberException
            if (m.getMemberID().equals(member.getMemberID())) {
                m.addToHistory(history);

                break;
            }
        }
        saveMembers(members);
    }
}
