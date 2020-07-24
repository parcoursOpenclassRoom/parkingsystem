package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.util.DateUtil;

import java.time.*;

public class FareCalculatorService {

    /**
     * parking rate calculation
     */
    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        //TODO: Some tests are failing here. Need to check if this logic is correct
        int freeMinutes = 30;
        int duration = (int) Duration.between(DateUtil.convertToLocalDateTime(ticket.getInTime()),
                DateUtil.convertToLocalDateTime(ticket.getOutTime()))
                .toMinutes();
        // check if he can benefit from free parking
        if(duration > freeMinutes) {
            double fareCarePerMinute = Fare.CAR_RATE_PER_HOUR / 60;
            double fareBikePerMinute = Fare.BIKE_RATE_PER_HOUR / 60;
            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR: {
                    ticket.setPrice(getTotal(duration * fareCarePerMinute, ticket.getDiscount()));
                    break;
                }
                case BIKE: {
                    ticket.setPrice(getTotal(duration * fareBikePerMinute, ticket.getDiscount()));
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unkown Parking Type");
            }
        }else{
            ticket.setPrice(0);
        }
    }

    private double getTotal(double price, double discount){
        return discount > 0 ? price - (price * discount/100 ) : price;
    }
}