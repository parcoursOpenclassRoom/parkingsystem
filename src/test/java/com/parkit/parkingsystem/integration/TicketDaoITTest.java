package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketDaoITTest {
    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;
    private final String vehicletype = "FGHJKLOU";

    @BeforeAll
    private static void setUp() throws Exception{
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }

    @Test
    public void testSaveTicket(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  45 * 60 * 1000) );
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber(vehicletype);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setDiscount(5);
        ticket.setPrice(10);
        ticket.setParkingSpot(parkingSpot);
        assertTrue(ticketDAO.saveTicket(ticket));
    }

    @Test
    public void testSaveTicketWithMissingExtiTime(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  45 * 60 * 1000) );
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber(vehicletype);
        ticket.setInTime(inTime);
        ticket.setDiscount(5);
        ticket.setPrice(10);
        ticket.setOutTime(null);
        ticket.setParkingSpot(parkingSpot);
        assertTrue(ticketDAO.saveTicket(ticket));
    }

    @Test
    public void testGetTicket(){
        testSaveTicket();
        assertNotNull(ticketDAO.getTicket(vehicletype));
    }

    @Test
    public void testUpdateTicket(){
        testGetTicket();
        Ticket ticket = ticketDAO.getTicket(vehicletype);
        ticket.setPrice(20);
        assertTrue(ticketDAO.updateTicket(ticket));
    }
}