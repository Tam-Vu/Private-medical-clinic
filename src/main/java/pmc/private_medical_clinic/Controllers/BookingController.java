package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.BillDto;
import pmc.private_medical_clinic.Dto.BookingDto;
import pmc.private_medical_clinic.Entity.Booking;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Services.BookingService;

import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/data/google-sheets/fetching")
    public ResponeInfo<String> fetchDataFromGoogleSheet() {
        ResponeInfo<String> response = new ResponeInfo<>();
        if (bookingService.fetchDataFromGoogleSheet()) {
            response.setStatusCode(200);
            response.setMessage("Successfully fetched data from google sheet");
        } else {
            response.setStatusCode(500);
            response.setMessage("Failed to fetch data from google sheet");
        }
        return response;
    }

    @GetMapping("/")
    public ResponeInfo<List<Booking>> getAllBookings() {
        ResponeInfo<List<Booking>> response = new ResponeInfo<>();
        if (bookingService.getAllBookings().isEmpty()) {
            response.setMessage("No booking found");
            return response;
        }
        response.setData(bookingService.getAllBookings());
        response.setMessage("success");
        response.setStatusCode(200);
        return response;
    }

    @PostMapping("/")
    public ResponeInfo<Booking> createBooking(@RequestBody BookingDto bookingDto) {
        ResponeInfo<Booking> response = new ResponeInfo<>();
        response.setData(bookingService.createBooking(bookingDto));
        return response;
    }

    @GetMapping("/{bookingDate}")
    public ResponeInfo<List<Booking>> getBookingByBookingDate(@PathVariable("bookingDate") String bookingDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // Specify the date format
        Date date = sdf.parse(bookingDate);
        ResponeInfo<List<Booking>> response = new ResponeInfo<>();
        response.setData(bookingService.getBookingByBookingDate(date));
        response.setStatusCode(200);
        return response;
    }

    @PutMapping("/{id}")
    public ResponeInfo<Booking> updateBooking(@PathVariable("id") Long id, @RequestBody BookingDto bookingDto) {
        ResponeInfo<Booking> response = new ResponeInfo<>();
        response.setData(bookingService.updateBooking(id, bookingDto));
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponeInfo<Boolean> deleteBooking(@PathVariable("id") Long id) {
        ResponeInfo<Boolean> response = new ResponeInfo<>();
        if (bookingService.deleteBooking(id)) {
            response.setMessage("Successfully deleted booking");
        } else {
            response.setMessage("Failed to delete booking");
        }
        return response;
    }
}
