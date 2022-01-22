package ec.com.ibs.kardexpro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.ibs.kardexpro.service.IArticuloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
public class ArticuloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArticuloService articuloService;

    @Autowired
    private ObjectMapper objectMapper;

    //JUnit test for
    @Test
    public void givenArticuloObject_whenCreateArticulo_thenReturnSavedArticulo() throws Exception {
        //given - precondition or setup
        /*ArticuloEntity articuloEntity = ArticuloEntity.builder()
                .codigoBarras("1111")
                .descripcion("Camiseta superman")
                .precio(1D)
                .existencia(2)
                .build();
        BDDMockito.given(articuloService.registrarArticulo(ArgumentMatchers.any(ArticuloEntity.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));
        //when - action or the behavior that we are going test
        ResultActions response = mockMvc.perform(post("/articulo/registrarArticulo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(articuloEntity)));
        //then - verify the result or output
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.codigoBarras",
                        CoreMatchers.is(articuloEntity.getCodigoBarras())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion",
                        CoreMatchers.is(articuloEntity.getDescripcion())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precio",
                        CoreMatchers.is(articuloEntity.getPrecio())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.existencia",
                        CoreMatchers.is(articuloEntity.getExistencia())));*/
    }

}
