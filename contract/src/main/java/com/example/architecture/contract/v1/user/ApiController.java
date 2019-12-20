package com.example.architecture.contract.v1.user;

import com.example.architecture.contract.v1.user.model.request.SharingRequest;
import com.example.architecture.contract.v1.user.model.request.UserRequest;
import com.example.architecture.contract.v1.user.model.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;

@AllArgsConstructor
@Api(value = "Contract Controller")
@RequestMapping("/v1/dropbox")
@RestController
public class ApiController {
    private UserContractFacade facade;

    @ApiOperation(value = "Consulta usuario pelo ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/user/{id}")
    public UserResponse findById(@PathVariable String id) {
        return facade.findById(id);
    }


    @ApiOperation(value = "Retorna uma lista com todos usuarios.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation", response = UserListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/user")
    public UserListResponse allUsers() {
        return facade.allUsers();
    }

    @ApiOperation(value = "Retorna uma lista do FTP com todos uploads feitos pelo usuario de ID informado.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/user/{id}/upload")
    @ResponseStatus(HttpStatus.OK)
    public UploadsListResponse allUploadsUser(@PathVariable String id) {
        return facade.allUploadsUser(id);
    }


    @ApiOperation(value = "Cria um usuario no DB e um Diretorio FTP nomeado com ID do usuario.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping({"/user"})
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest user) {
        return facade.createUser(user);
    }


    @ApiOperation(value = "Deleta um usuario pelo ID e seu diretorio junto com seus registros de compartilhamento de uploads.")
    @DeleteMapping("/user/{id}")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted User"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        facade.deleteById(id);
    }


    @ApiOperation(value = "Deleta um upload do usuario de ID x passando o nome do Arquivo como referencia.")
    @DeleteMapping("/user/{id}/upload")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted File"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "File not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUploadUser(@PathVariable String id, @RequestParam String nameFile) {
        facade.deleteUploadUser(id, nameFile);
    }

    @ApiOperation(value = "Atualiza um usuario existente.")
    @PutMapping("/user/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated User"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@Valid @RequestBody UserRequest user, @PathVariable String id) {
        return facade.userUpdate(user, id);
    }


    @ApiOperation(value = "Faz upload de um arquivo para o servidor FTP passando o ID do user como caminho para o diretorio.")
    @PostMapping("/user/{id}/upload")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.OK)
    public void userUpload(@PathVariable String id, @RequestParam MultipartFile file) {
        facade.userUpload(id, file);
    }


    //Todo rever metodos
    @ApiOperation(value = "Cria o registro de um compartilhamento passando IdFonte, NomeDoArquivo, IdRecebente.")
    @PostMapping({"/share"})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Share created"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public SharingResponse createdSharedtFile(@Valid @RequestBody SharingRequest compart) {
        return facade.createdSharedtFile(compart);
    }

    //Todo refatorar busca de compartilhamentos
    @ApiOperation(value = "Retorna uma lista com todos compartilhamentos feitos pelo IdFonte referenciado.")
    @GetMapping({"user/{id}/share/source"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Share found"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "Share not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ResponseStatus(HttpStatus.OK)
    public SharingListResponse getSharedIdSource(@PathVariable String id) {
        return facade.getSahredIdSource(id);
    }


    @ApiOperation(value = "Retorna uma lista com todos compartilhamentos que tenha o nome do arquivo referenciado.")
    @GetMapping({"/share/shareName/{name}"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Share found"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "Share not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ResponseStatus(HttpStatus.OK)
    public SharingListResponse getSahredfileName(@PathVariable String name) {
        return facade.getSahredfileName(name);
    }
// Todo refatorar
    @ApiOperation(value = "Retorna uma lista com todos compartilhamentos feitos pelo IdRecebente referenciado.")
    @GetMapping({"/share/recipient/{id}"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Share found"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "Share not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ResponseStatus(HttpStatus.OK)
    public SharingListResponse getSahredRecipientId(@PathVariable String id) {
        return facade.getSahredRecipientId(id);
    }

    @ApiOperation(value = "Delete um compartilhamento passando Id como referencia.")
    @DeleteMapping("/share/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted Share"),
            @ApiResponse(code = 401, message = "Unauthorized Method"),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "Share not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSharedFindById(@PathVariable String id) {
        facade.deleteSharedFindById(id);
    }

}
