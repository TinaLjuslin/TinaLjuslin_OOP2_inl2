package com.ljuslin.service;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.Member;
import com.ljuslin.model.Rental;
import com.ljuslin.repository.MemberRegistry;
import com.ljuslin.model.Level;
import com.ljuslin.repository.RentalRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Handles members in this shop
 *
 * @author Tina Ljuslin
 */
public class MembershipService {
    private MemberRegistry memberRegistry;
    private RentalRepository rentalRepo;

    public MembershipService() {
    }

    public MembershipService(MemberRegistry memberRegistry, RentalRepository rentalRepo) {
        this.memberRegistry = memberRegistry;
        this.rentalRepo = rentalRepo;
    }

    public void newMember(String firstName, String lastName, Level level)
            throws MemberException, FileException {
        if (firstName.trim().equals("")) {
            throw new MemberException("Vänligen ange ett förnamn.");
        } else if (lastName.trim().equals("")) {
            throw new MemberException("Vänligen ange ett efternamn.");
        } else if (level == null) {
            throw new MemberException("Vänligen välj en level.");
        }

        Member member = new Member(firstName, lastName, level);
        memberRegistry.addMember(member);
        memberRegistry.addToHistory(member,
                (getTimeString() + " Member added: " + member.toString()));
    }

    public String getTimeString() {
        return LocalDateTime.now().format(DateTimeFormatter.
                ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

    public List<Member> getAllMembers() throws FileException {
        return memberRegistry.getMembers();

    }

    public Member getMember(String memberID) throws MemberException, FileException {
        return memberRegistry.getMember(memberID);

    }

    public List<Member> searchMembers(String search) throws MemberException, FileException {
        List<Member> searchmembers = memberRegistry.getMembers().stream()
                .filter(m -> m.toString().toLowerCase().contains(search.toLowerCase()))
                .toList();
        if (searchmembers.isEmpty()) {
            throw new MemberException("Inga medlemmar passar in på dina sökkriterier");
        }
        return searchmembers;
    }

    public void changeMember(Member member) throws MemberException, FileException {
        if (member.getFirstName().trim().equals("")) {
            throw new MemberException("Vänligen ange ett förnamn.");
        } else if (member.getLastName().trim().equals("")) {
            throw new MemberException("Vänligen ange ett efternamn.");
        } else if (member.getMemberLevel() == null) {
            throw new MemberException("Vänligen välj en level.");
        }
        memberRegistry.changeMember(member);
        addToHistory(member, ("Member changed: " + member.toString()));

    }

    public void addToHistory(Member member, String history) throws FileException, MemberException {
        String time = getTimeString();
        String historyWithTime = time + " : " + history;
        memberRegistry.addToHistory(member, historyWithTime);
    }

    public void removeMember(Member member) throws MemberException, FileException {
        List<Rental> rentals = rentalRepo.getRentals();
        for (Rental rental : rentals) {
            if (rental.getMember().getMemberID().equals(member.getMemberID()) && rental.getReturnDate() == null) {
                throw new MemberException("Medlemmen kan inte tas bort då hen har en pågående " +
                        "uthyrning.");
            }
        }
        memberRegistry.removeMember(member);
    }
}