package com.substring.chat.controllers;

import com.substring.chat.config.AppConstants;
import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.payload.MessageRequest;
import com.substring.chat.repositories.RoomRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.FrameworkServlet;

import java.time.LocalDateTime;

@Controller
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
public class ChatController {

    private final FrameworkServlet frameworkServlet;
    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository, FrameworkServlet frameworkServlet) {
        this.roomRepository = roomRepository;
        this.frameworkServlet = frameworkServlet;
    }

    //for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId,
                               @RequestBody MessageRequest request) {
        Room room = roomRepository.findByRoomId(request.getRoomId());

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if(room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
        }else{
            throw new RuntimeException("Room not found");
        }
        return message;
    }

}
