package org.ticket.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.ticket.domain.entity.Event;
import org.ticket.exception.EventNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Optional;


@Repository
public class EventRepository {
    private final JdbcTemplate jdbcTemplate;

    public EventRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Event> findById(Long eventId){
        String sql = "SELECT * FROM events WHERE id = ?";
        try {
            Event event = jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> new Event(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("seats_left"))
                    , eventId);
            return Optional.ofNullable(event);
        } catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    public void update(Event event){
        String sql = "UPDATE events " +
                "SET name = ?, seats_left = ? " +
                "WHERE id = ?;";
        boolean affected = jdbcTemplate.update(sql, event.getName(), event.getSeatsLeft(), event.getId()) > 0;
        if (!affected) throw new EventNotFoundException(event.getId());
    }

    public Event create(Event event){
        String sql = "INSERT INTO events (name, seats_left) " +
                "VALUES (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, event.getName());
            ps.setInt(2, event.getId());
            return ps;
        }, keyHolder);

        event.setId(keyHolder.getKey().intValue());
        return event;
    }
}
