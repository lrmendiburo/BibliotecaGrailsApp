package biblioteca3

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class OperacionController {

    OperacionService operacionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond operacionService.list(params), model:[operacionCount: operacionService.count()]
    }

    def show(Long id) {
        respond operacionService.get(id)
    }

    def create() {
        respond new Operacion(params)
    }

    def save(Operacion operacion) {
        if (operacion == null) {
            notFound()
            return
        }

        try {
            operacionService.save(operacion)
        } catch (ValidationException e) {
            respond operacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'operacion.label', default: 'Operacion'), operacion.id])
                redirect operacion
            }
            '*' { respond operacion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond operacionService.get(id)
    }

    def update(Operacion operacion) {
        if (operacion == null) {
            notFound()
            return
        }

        try {
            operacionService.save(operacion)
        } catch (ValidationException e) {
            respond operacion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'operacion.label', default: 'Operacion'), operacion.id])
                redirect operacion
            }
            '*'{ respond operacion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        operacionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'operacion.label', default: 'Operacion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'operacion.label', default: 'Operacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
