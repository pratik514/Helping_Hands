package Application.Repo;

import org.springframework.data.repository.CrudRepository;

import Application.Models.CustomerDto;

public interface UserRepo extends CrudRepository<CustomerDto, Integer> {

}
