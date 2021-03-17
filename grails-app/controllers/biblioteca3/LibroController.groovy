package biblioteca3

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class LibroController {

    LibroService libroService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond libroService.list(params), model:[libroCount: libroService.count()]
    }

    def show(Long id) {
        respond libroService.get(id)
    }

    def create() {
        respond new Libro(params)
    }

    def save(Libro libro) {
        if (libro == null) {
            notFound()
            return
        }

        try {
            libroService.save(libro)
        } catch (ValidationException e) {
            respond libro.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'libro.label', default: 'Libro'), libro.id])
                redirect libro
            }
            '*' { respond libro, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond libroService.get(id)
    }

    def update(Libro libro) {
        if (libro == null) {
            notFound()
            return
        }

        try {
            libroService.save(libro)
        } catch (ValidationException e) {
            respond libro.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'libro.label', default: 'Libro'), libro.id])
                redirect libro
            }
            '*'{ respond libro, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        libroService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'libro.label', default: 'Libro'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'libro.label', default: 'Libro'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
