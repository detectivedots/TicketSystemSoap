package org.ticket.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.ticket.domain.entity.Ticket;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository
public class TicketRepository {
    private final JdbcTemplate jdbcTemplate;

    public TicketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Ticket create(Ticket ticket){
        String sql = "INSERT INTO tickets (event_id, owner_name, booking_time) " +
                "VALUES (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setLong(1, ticket.getEvent().getId());
            ps.setString(2, ticket.getOwnerName());
            ps.setTimestamp(
                    3,
                    Timestamp.valueOf(ticket.getBookingTime())
            );

            return ps;
        }, keyHolder);

        ticket.setId(keyHolder.getKey().intValue());
        return ticket;
    }
}
