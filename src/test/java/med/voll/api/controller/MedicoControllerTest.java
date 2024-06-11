package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEnderecoDTO;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosCadastroMedicoDTO> dadosCadastroMedicoDTOJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoMedicoDTO> dadosDetalhamentoMedicoDTOJson;
    @MockBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Deveria devolver código 200 quando informações do médico estão válidas")
    @WithMockUser
    void cadastrar() throws Exception {
        DadosEnderecoDTO endereco = dadosEndereco();
        DadosDetalhamentoMedicoDTO dadosDetalhamentoMedicoDTO = dadosDetalhamentoMedicoDTO(endereco);
        MockHttpServletResponse response =
                mvc
                        .perform(
                                post("/medicos")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(dadosCadastroMedicoDTOJson.write(
                                                dadosCadastroMedico(endereco)
                                        ).getJson())
                        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        String jsonEsperado = dadosDetalhamentoMedicoDTOJson
                .write(
                        dadosDetalhamentoMedicoDTO
                ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DadosCadastroMedicoDTO dadosCadastroMedico(DadosEnderecoDTO endereco) {
        return new DadosCadastroMedicoDTO("Medico",
                "medico@voll.med",
                "61988887777",
                "123456",
                Especialidade.CARDIOLOGIA,
                endereco);
    }

    private DadosEnderecoDTO dadosEndereco() {
        return new DadosEnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

    private DadosDetalhamentoMedicoDTO dadosDetalhamentoMedicoDTO(DadosEnderecoDTO endereco) {
        return new DadosDetalhamentoMedicoDTO(null ,
                "Medico",
                "medico@voll.med",
                "123456",
                "61988887777",
                Especialidade.CARDIOLOGIA,
                new Endereco(endereco)
        );
    }

}