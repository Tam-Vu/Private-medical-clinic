package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.Booking;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE  b.bookingDate = :bookingDate AND b.phoneNumber = :phoneNumber")
    Booking findValidData(Date bookingDate, String phoneNumber);

    @Query("SELECT b FROM Booking b")
    List<Booking> findAllBookings();

    @Query("SELECT b FROM Booking b WHERE  b.bookingDate = :bookingDate")
    List<Booking> findAllBookingsByBookingDate(Date bookingDate);
}
