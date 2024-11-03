package mutantes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @Test
    public void testIsMutant_HorizontalMatch() throws Exception {
        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dna\": [\"ATGCGA\", \"CGGTGC\", \"TAAAAA\", \"AGAAGG\",\"CTCCTA\",\"TCACTG\"]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testIsMutant_VerticalMatch() throws Exception {
        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dna\": [\"CAGTGC\",\"TAATGT\",\"AAGAGG\", \"CACCTA\", \"TCACTG\"]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testIsMutant_DiagonalMatch() throws Exception {
        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testIsNotMutant() throws Exception {
        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dna\": [\"ATGCGC\", \"CAGTAC\", \"TTAGTA\", \"AGCTGG\", \"TCAGTA\", \"TCACTC\"]}"))
                .andExpect(status().isForbidden());
    }
}
