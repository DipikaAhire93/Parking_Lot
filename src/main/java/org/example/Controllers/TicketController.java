package org.example.Controllers;

import org.example.DTO.IssueTicketRequestDTO;
import org.example.DTO.IssueTicketResponseDTO;
import org.example.DTO.ResponseStatus;
import org.example.Models.Ticket;
import org.example.Services.TicketService;

//controller cant throw exceptions.

public class TicketController {

    private TicketService ticketService;

    //constructor
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO issueTicketRequestDTO){

        IssueTicketResponseDTO response=new IssueTicketResponseDTO();
        Ticket ticket;

        try{
            ticket= ticketService.issueTicket(
                    issueTicketRequestDTO.getVehicleType(),
                    issueTicketRequestDTO.getVehicleNumber(),
                    issueTicketRequestDTO.getVehicleOwnerName(),
                    issueTicketRequestDTO.getGateId()
            );
        }
        catch (Exception ex){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setFailureReason(ex.getMessage());
            return response;
        }

        response.setResponseStatus(ResponseStatus.SUCESS);
        response.setTicket(ticket);
        return response;
    }
}
