package events;

import models.AppointmentCart;

public class AppointmentCardCreated {
    AppointmentCart appointmentCart;

    public AppointmentCardCreated(AppointmentCart appointmentCart) {
        this.appointmentCart = appointmentCart;
    }

    public AppointmentCart getAppointmentCart() {
        return appointmentCart;
    }
}
