/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AppointmentListDto;
import pmc.private_medical_clinic.Entity.AppointmentList;
import pmc.private_medical_clinic.Repositories.AppointmentListRepo;

/**
 *
 * @author duyth
 */
@Service
public class AppointmentListServiceIml implements AppointmentListService {

    @Autowired
    private AppointmentListRepo appointmentListRepo;

    @Override
    public List<AppointmentList> getAllAppointmentList() {
        return appointmentListRepo.findAll();
    }

    @Override
    public AppointmentList createAppointmentList(AppointmentListDto appointmentListDto) {
        AppointmentList existingAppointment = appointmentListRepo.findScheduleByDate(appointmentListDto.getScheduleDate());
        if (existingAppointment != null) {
            return existingAppointment;
        }

        AppointmentList appointmentList = new AppointmentList();
        appointmentList.setScheduleDate(appointmentListDto.getScheduleDate());
        appointmentListRepo.save(appointmentList);
        return appointmentList;
    }

    @Override
    public AppointmentList getAppointmentListById(Long id) {
        return appointmentListRepo.findScheduleById(id);
    }

}
