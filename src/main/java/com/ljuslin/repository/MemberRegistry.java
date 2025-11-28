package com.ljuslin.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ljuslin.exception.FileException;
import com.ljuslin.model.Member;
import com.ljuslin.model.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Holds all members of this rental shop
 * @author Tina Ljuslin
 */
public class MemberRegistry {
    private List<Member> members = new ArrayList<>();
    private final String FILENAME = "members.json";
    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
    private final File memberFile = new File(FILENAME);
    /**
     * Constructor, creates a few members for testing
     */
    public MemberRegistry() {
}

    /**
     * Returns a member for a specific ID
     * @param id, the id to search
     * @return the member
     */
    public Member getMember(String id) {
   /*     for (Member member : members) {
            if (member.getMemberID().equals(id)) {
                return member;
            }
        }
   */     return null;
    }

    /**
     * Changes a members last name and level
     * @param memberID id of member to change
     * @param lastName new last name
     * @param level new level
     * @return the new member
     */
    public Member changeMember(String memberID, String lastName, Level level) {
  /*      for (Member m : members) {
            if (m.getMemberID().equals(memberID)) {
                m.setLastName(lastName);
                m.setMemberLevel(level);
                return m;
            }
        }
  */      return null;
    }

    /**
     * Changes a members last name
     * @param memberID id of member to change
     * @param lastName new last name
     * @return the new member
     */
    public Member changeMember(String memberID, String lastName) {
/*
        for (Member m : members) {
            if (m.getMemberID().equals(memberID)) {
                m.setLastName(lastName);
                return m;
            }
        }
*/
        return null;
    }

    /**
     * Changes a members level
     * @param memberID id of member to change
     * @param level new level
     * @return the new member
     */
    public Member changeMember(String memberID, Level level) {
/*
        for (Member m : members) {
            if (m.getMemberID().equals(memberID)) {
                m.setMemberLevel(level);
                return m;
            }
        }
*/
        return null;
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
            return Arrays.asList(
                    mapper.readValue(memberFile, Member[].class));
        } catch (Exception e) {
            throw new FileException("Medlemsfilen kunde ej läsas");
        }
    }
    public void addMember(Member member) throws FileException {
        try {
            List<Member> members = new ArrayList<>(Arrays.asList(mapper.readValue(getClass().getClassLoader().getResourceAsStream(FILENAME),
                    Member[].class)));
            members.add(member);
            //writer.writeValue(new File(FILENAME), members);
            writer.writeValue(memberFile, members);

        } catch (Exception e) {
            throw new FileException("Den nya medlemmen kunde ej sparas");
        }
    }
}
