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
    //private List<Member> members = new ArrayList<>();
    private final String FILENAME = "members.json";
    private ObjectMapper mapper = new ObjectMapper();
    private final File memberFile = new File(FILENAME);

    /**
     * Constructor, creates a few members for testing
     */
    public MemberRegistry() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (!memberFile.exists()) {
            try {
                mapper.writeValue(memberFile, new ArrayList<Member>());
            } catch (IOException e) {
                // Hantera fel om filen inte kan skapas
                System.err.println("VARNING: Kunde inte skapa initial medlemsfil.");
            }
        }
}
    private void saveMembers(List<Member> members) throws FileException {
        try {
            // Skriver till den definierade skrivbara filen
            mapper.writeValue(memberFile, members);
        } catch (IOException e) {
            throw new FileException("Kunde ej spara medlemmar till fil");
        }
    }
    /**
     * Returns a member for a specific ID
     * @param id, the id to search
     * @return the member
     */
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

    /**
     * Removes a member
     * @param memberID, id of member to remove
     * @return the removed member or null if no member could be removed
     */
    public Member removeMember(String memberID) {
     /*   for (Member m : members) {
            if (m.getMemberID().equals(memberID)) {
                members.remove(m);
                return m;
            }
        }
     */   return null;
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
            List<Member> members = new ArrayList<>(Arrays.asList(mapper.readValue(getClass().getClassLoader().getResourceAsStream(FILENAME),
                    Member[].class)));
            members.add(member);
            mapper.writeValue(new File(FILENAME), members);
        } catch (Exception e) {
            throw new FileException("Den nya medlemmen kunde ej sparas");
        }
    }
    //private void saveMembersToFile()
}
