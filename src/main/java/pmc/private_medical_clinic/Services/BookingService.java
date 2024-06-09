package pmc.private_medical_clinic.Services;

import org.springframework.web.bind.annotation.RequestBody;
import pmc.private_medical_clinic.Dto.BookingDto;
import pmc.private_medical_clinic.Entity.Booking;

import java.util.List;

public interface BookingService {
    boolean fetchDataFromGoogleSheet();
    Booking createBooking(BookingDto bookingDto);
    Booking updateBooking(Long Id,BookingDto bookingDto);
    Boolean deleteBooking(Long Id);
    List<Booking> getAllBookings();
    Booking getBookingById(Long Id);
}
