package cl.pixysoft.sysreserva.infraestructure;

import cl.pixysoft.sysreserva.domain.modelo.entities.Pais_Entity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Pais_PanacheRepository implements PanacheRepository<Pais_Entity> {

    public Pais_PanacheRepository() {}

//    //Acá puedo incorporar mís propios métodos, consultas customizadas, o hacer un override
//    //de algún método ya existente en PanacheRepository y hacerlo disponible como parte del repositorio
//
//    //Por ejemplo, agregar este método para obtener una lista ordenada por código de país
//    public List<Pais_Modelo> listAllPaisOrderByCodigoPaisDesc() {
//        return listAll(Sort.descending("codigoPais"));
//    }
//
//    //O obtener todos los países que su nombre comienza con "C"
//    public List<Pais_Modelo> listAllPaisNameStartsWithC() {
//        //String filter = "C";
//        //return list("nombre LIKE '?1%'", Sort.by("nombre"), filter);
//        return list("nombre LIKE 'C%'", Sort.by("nombre"));
//    }
}
