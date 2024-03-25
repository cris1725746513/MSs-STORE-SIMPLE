package cl.pixysoft.sysreserva.infraestructure;

import cl.pixysoft.sysreserva.domain.modelo.dtos.CategoriaDto;
import cl.pixysoft.sysreserva.domain.modelo.dtos.ProductoDto;
import cl.pixysoft.sysreserva.domain.modelo.entities.Producto;
import cl.pixysoft.sysreserva.domain.modelo.mappers.CategoriaMapper;
import cl.pixysoft.sysreserva.domain.modelo.mappers.ProductoMapper;
import cl.pixysoft.sysreserva.domain.puertosSalida.ProductoPuertoSalida;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductoDB implements ProductoPuertoSalida {

    @Inject
    ProductoRepository repository;

    @Inject
    ProductoMapper mapper;

    @Inject
    CategoriaMapper categoriaMapper;

    @Inject
    CategoriaRepository categoriaRepository;


    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public ProductoDto crearProducto_PuertoSalida(ProductoDto productoNew) {
        Producto producto = mapper.toEntity(productoNew);
        try{
        repository.persist(producto);}
        catch (Exception e){
            System.out.println(e);
            return mapper.toDto(null);
        }
        return mapper.toDto(repository.find("codigo",productoNew.codigo).firstResult());
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public List<ProductoDto> obtenerTodosProducto_PuertoSalida() {
        List<ProductoDto> productos = repository.
                findAll().list().stream()
                .map(producto -> mapper.toDto(producto))
                .collect(Collectors.toList());

        return productos;
    }

    @Override
    public List<CategoriaDto> obtenerTodosCategoria_PuertoSalida() {
        List<CategoriaDto> categorias = categoriaRepository.
                findAll().list().stream()
                .map(categoria -> categoriaMapper.toDto(categoria))
                .collect(Collectors.toList());

        return categorias;
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public ProductoDto obtenerProducto_PuertoSalida(String codigo) {
       Producto producto = repository.find("codigo",codigo).firstResult();
        return mapper.toDto(producto);
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public ProductoDto actualizarProducto_PuertoSalida(ProductoDto productoUpdate) {
        Producto producto = repository.find("id",productoUpdate.id).firstResult();
        if(Objects.nonNull(producto)){
            if(Objects.nonNull(productoUpdate.codigo)){
                producto.setCodigo(productoUpdate.codigo);}

            if(Objects.nonNull(productoUpdate.iva)){
                producto.setIva(productoUpdate.iva);}

            if(Objects.nonNull(productoUpdate.pvp)){
                producto.setPvp(productoUpdate.pvp);}

            if(Objects.nonNull(productoUpdate.detalle)){
                producto.setDetalle(productoUpdate.detalle);}

            if(Objects.nonNull(productoUpdate.existente)){
                producto.setExistente(productoUpdate.existente);}

            if(Objects.nonNull(productoUpdate.costoProveedor)){
                producto.setCostoProveedor(productoUpdate.costoProveedor);}

            if(Objects.nonNull(productoUpdate.nombre)){
                producto.setNombre(productoUpdate.nombre);}

            if(Objects.nonNull(productoUpdate.proveedor)){
                producto.setProveedor(productoUpdate.proveedor);}
        }else {
            return mapper.toDto(null);
        }
        return mapper.toDto(producto);
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public void eliminarProducto_PuertoSalida(long id) {
        Producto producto = repository.findById(id);
        if(Objects.nonNull(producto)){
            repository.deleteById(id);
        }
    }
}
