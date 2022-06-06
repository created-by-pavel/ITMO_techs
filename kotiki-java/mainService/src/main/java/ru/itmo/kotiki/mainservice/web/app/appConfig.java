package ru.itmo.kotiki.mainservice.web.app;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.mainservice.service.convertation.Convert;
import ru.itmo.kotiki.mainservice.service.convertation.ConvertImpl;
import ru.itmo.kotiki.mainservice.web.security.DecodeTokenUtil;

@Configuration
@Component
@EnableJpaRepositories(basePackages = {"ru.itmo.kotiki.mainservice.*"})
@EntityScan(basePackages = {"ru.itmo.kotiki.*"})
@ComponentScan(basePackages = {"ru.itmo.kotiki.mainservice.**"})
public class appConfig {
    public static final String ADD_QUEUE = "addQueue";
    public static final String GET_ALL_QUEUE = "getAllQueue";
    public static final String GET_BY_ID_QUEUE = "getByIdQueue";
    public static final String GET_BY_COLOR_QUEUE = "getByColorQueue";
    public static final String GET_FRIENDS_QUEUE = "getFriendsQueue";
    public static final String DELETE_QUEUE = "deleteQueue";
    public static final String UPDATE_QUEUE = "updateQueue";
    public static final String SET_FRIENDS_QUEUE = "setFriendsQueue";
    public static final String ADD_OWNER_QUEUE = "addOwnerQueue";
    public static final String GET_ALL_OWNERS_QUEUE = "getAllOwnersQueue";
    public static final String GET_OWNER_BY_ID_QUEUE = "getOwnerByIdQueue";
    public static final String DELETE_OWNER_QUEUE = "deleteOwnerQueue";
    public static final String UPDATE_OWNER_QUEUE = "updateOwnerQueue";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue addOwnerQueue() {
        return new Queue(ADD_OWNER_QUEUE, false, false, true);
    }

    @Bean
    public Queue getOwnerAllQueue() {
        return new Queue(GET_ALL_OWNERS_QUEUE, false, false, true);
    }

    @Bean
    public Queue getOwnerByIdQueue() {
        return new Queue(GET_OWNER_BY_ID_QUEUE, false, false, true);
    }

    @Bean
    public Queue deleteOwnerQueue() {
        return new Queue(DELETE_OWNER_QUEUE, false, false, true);
    }

    @Bean
    public Queue updateOwnerQueue() {
        return new Queue(UPDATE_OWNER_QUEUE, false, false, true);
    }

    @Bean
    public Queue addQueue() {
        return new Queue(ADD_QUEUE, false, false, true);
    }

    @Bean
    public Queue getAllQueue() {
        return new Queue(GET_ALL_QUEUE, false, false, true);
    }

    @Bean
    public Queue getByIdQueue() {
        return new Queue(GET_BY_ID_QUEUE, false, false, true);
    }

    @Bean
    public Queue getByColorQueue() {
        return new Queue(GET_BY_COLOR_QUEUE, false, false, true);
    }

    @Bean
    public Queue getFriendsQueue() {
        return new Queue(GET_FRIENDS_QUEUE, false, false, true);
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue(DELETE_QUEUE, false, false, true);
    }

    @Bean
    public Queue updateQueue() {
        return new Queue(UPDATE_QUEUE, false, false, true);
    }

    @Bean
    public Queue setFriendsQueue() {
        return new Queue(SET_FRIENDS_QUEUE, false, false, true);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Convert convert() {
        return new ConvertImpl();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DecodeTokenUtil decodeTokenUtil() {
        return new DecodeTokenUtil();
    }

}
