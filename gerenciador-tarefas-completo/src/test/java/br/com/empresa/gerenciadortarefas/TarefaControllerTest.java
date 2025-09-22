
package br.com.empresa.gerenciadortarefas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarNovaTarefa() throws Exception {
        String json = "{\"titulo\":\"Estudar DevOps\", \"descricao\":\"Praticar CI/CD\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("Estudar DevOps"));
    }

    @Test
    void deveListarTarefas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tarefas"))
                .andExpect(status().isOk());
    }
}
