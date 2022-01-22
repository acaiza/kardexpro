package ec.com.ibs.kardexpro.service;

import ec.com.ibs.kardexpro.model.ArticuloEntity;
import ec.com.ibs.kardexpro.repository.IArticuloRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticuloServiceTest {
    //@Mock
    @MockBean
    IArticuloRepository articuloRepository;
    // @InjectMocks
    @Autowired
    IArticuloService articuloService;

    //JUnit test for
    @Test
    public void givenArticuloEntity_whenSavedArticuloEntity_thenArticuloEntitySave() {
        //given - precondition or setup
        ArticuloEntity articuloEntity = ArticuloEntity.builder()
                .idCategoria(1)
                .codigoBarras("777")
                .descripcion("Camiseta superman")
                .precio(7d)
                .existencia(7)
                .build();
        //when - action or the behavior that we are going test
        articuloService.registrarArticulo(articuloEntity);
        //then - verify output
        verify(articuloRepository, times(1)).registrarArticulo(articuloEntity);
    }

    //JUnit test for
    @Test
    public void given_when_then() {
        when(articuloRepository.obtenerArticulos(Boolean.FALSE)).thenReturn(new ArrayList<>());
        assertThat(articuloService.obtenerArticulos()).isEmpty();
        verify(articuloRepository, times(1)).obtenerArticulos(Boolean.FALSE);
    }

}
