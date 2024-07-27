package ma.zs.zyn.unit.dao.facade.core.support;

import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import ma.zs.zyn.dao.facade.core.support.CustumorSupportConversationStateDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CustumorSupportConversationStateDaoTest {

@Autowired
    private CustumorSupportConversationStateDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        CustumorSupportConversationState entity = new CustumorSupportConversationState();
        entity.setCode(code);
        underTest.save(entity);
        CustumorSupportConversationState loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        CustumorSupportConversationState loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        CustumorSupportConversationState entity = new CustumorSupportConversationState();
        entity.setId(id);
        underTest.save(entity);
        CustumorSupportConversationState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        CustumorSupportConversationState entity = new CustumorSupportConversationState();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        CustumorSupportConversationState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<CustumorSupportConversationState> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<CustumorSupportConversationState> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        CustumorSupportConversationState given = constructSample(1);
        CustumorSupportConversationState saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private CustumorSupportConversationState constructSample(int i) {
		CustumorSupportConversationState given = new CustumorSupportConversationState();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
