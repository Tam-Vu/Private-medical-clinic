package pmc.private_medical_clinic.Services;

import ch.qos.logback.classic.Logger;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.BookingDto;
import pmc.private_medical_clinic.Entity.Booking;
import pmc.private_medical_clinic.Repositories.BookingRepo;
import pmc.private_medical_clinic.utils.GoogleSheetUtils;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private final BookingRepo bookingRepo;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(BookingServiceImpl.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_DETAIL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final String spreadsheetId = "1fqCqK5lKWEE9Szga5stHt8D8cqecAarIC4Y_f_FUwjI";

    BookingServiceImpl(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    @Override
    public boolean fetchDataFromGoogleSheet() {
        DATE_FORMAT.setLenient(false);
        try {
            ValueRange valueRange = GoogleSheetUtils.getValues(spreadsheetId, "Sheet1");
            List<List<Object>> values = valueRange.getValues();
            if (values == null || values.isEmpty()) {
                System.out.println("No data found.");
            } else {
                int rowCount = 0;
                for (List<Object> row : values) {
                    if (rowCount == 0) {
                        rowCount++;
                        continue;
                    }
                    Date bookingAppointment = DATE_FORMAT.parse((String) row.get(6));
                    Date bookingDate = DATE_FORMAT_DETAIL.parse((String) row.get(0));
                    Long birthYear = Long.parseLong((String) row.get(7));
                    if (bookingRepo.findValidData(bookingDate, (String) row.get(3)) == null) {
                        BookingDto bookingDto = new BookingDto();
                        bookingDto.setBookingDate(bookingDate);
                        bookingDto.setFullName((String) row.get(2));
                        bookingDto.setPhoneNumber((String) row.get(3));
                        bookingDto.setGender((String) row.get(4));
                        bookingDto.setAddress((String) row.get(5));
                        bookingDto.setBookingAppointment(bookingAppointment);
                        bookingDto.setBirthYear(birthYear);
                        Booking booking = new Booking();
                        booking.setBookingDate(bookingDto.getBookingDate());
                        booking.setFullName(bookingDto.getFullName());
                        booking.setPhoneNumber(bookingDto.getPhoneNumber());
                        booking.setGender(bookingDto.getGender());
                        booking.setAddress(bookingDto.getAddress());
                        booking.setBookingAppointment(bookingDto.getBookingAppointment());
                        booking.setBirthYear(bookingDto.getBirthYear());
                        bookingRepo.save(booking);
                    } else {
                        continue;
                    }
                }
            }
            return true;
        } catch (Exception e) {

            logger.error("Error occurred while fetching data from Google Sheet", e);
        }
        return false;
    }

    @Override
    public Booking createBooking(BookingDto bookingDto) {
        if (bookingRepo.findValidData(bookingDto.getBookingDate(), bookingDto.getPhoneNumber()) == null) {
            Booking booking = new Booking();
            booking.setBookingDate(bookingDto.getBookingDate());
            booking.setFullName(bookingDto.getFullName());
            booking.setPhoneNumber(bookingDto.getPhoneNumber());
            booking.setGender(bookingDto.getGender());
            booking.setAddress(bookingDto.getAddress());
            booking.setBookingAppointment(bookingDto.getBookingAppointment());
            booking.setBirthYear(bookingDto.getBirthYear());
            bookingRepo.save(booking);
        }
        return null;
    }

    @Override
    public Booking updateBooking(Long Id, BookingDto bookingDto) {
        Booking booking = bookingRepo.findById(Id).get();
        booking.setBookingAppointment(bookingDto.getBookingAppointment());
        booking.setStatus("Accepted");
        bookingRepo.save(booking);
        return booking;
    }

    @Override
    public Boolean deleteBooking(Long Id) {
        Booking booking = bookingRepo.findById(Id).orElse(null);
        if (booking != null) {
            booking.setStatus("Cancelled");
            bookingRepo.save(booking);
            return true;
        }
        return false;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAllBookings();
    }

    @Override
    public List<Booking> getBookingByBookingDate(Date bookingDate) {
        return bookingRepo.findAllBookingsByBookingDate(bookingDate);
    }
}
