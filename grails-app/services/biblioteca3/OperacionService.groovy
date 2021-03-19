package biblioteca3

import grails.gorm.services.Service

@Service(Operacion)
interface OperacionService {

    Operacion get(Serializable id)

    List<Operacion> list(Map args)

    Long count()

    void delete(Serializable id)

    Operacion save(Operacion operacion)

}