package org.based;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    private final String BUDDY_JSON = "{\n" +
            "    \"name\":\"Michael\",\n" +
            "    \"phone\":\"1234567890\"\n" +
            "}";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddRemove() throws Exception {
        //control (prove that the book didn't exist before
        this.mockMvc.perform(get("/books/1")).andDo(print()).andExpect(status().isNotFound());
        //actual test
        this.mockMvc.perform(post("/books")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/books/1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(not(containsString("Michael"))));
        this.mockMvc.perform(post("/books/1").contentType(MediaType.APPLICATION_JSON).content(BUDDY_JSON)).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/books/1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Michael")));
        this.mockMvc.perform(delete("/books/1/1")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/books/1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(not(containsString("Michael"))));
    }
}
