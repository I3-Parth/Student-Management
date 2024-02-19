package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.AddressesDTO;
import com.springboot.studentManagement.model.student;
import com.springboot.studentManagement.dto.ShowStudentAddressesDTO;
import com.springboot.studentManagement.model.Address;
import com.springboot.studentManagement.repository.studentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "locality", target = "locality")
    @Mapping(source = "landmark", target = "landmark")
    @Mapping(source = "city", target = "city")
    Address DTOToEntity(AddressesDTO addressesDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "locality", target = "locality")
    @Mapping(source = "landmark", target = "landmark")
    @Mapping(source = "city", target = "city")
    AddressesDTO EntityToDTO(Address address);

    List<Address> getEntityList(List<AddressesDTO> addressesDTOS);
    List<AddressesDTO> getDTOList(List<Address> addresses);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "locality", target = "locality")
    @Mapping(source = "landmark", target = "landmark")
    @Mapping(source = "city", target = "city")
    void updateAddress(AddressesDTO addressesDTO, @MappingTarget Address address);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "locality", target = "locality")
    @Mapping(source = "landmark", target = "landmark")
    @Mapping(source = "city", target = "city")
    default void updateAddresses(List<AddressesDTO> addressesDTOS, @MappingTarget List<Address> addresses){
        if(addressesDTOS != null){
            for (AddressesDTO addressesDTO: addressesDTOS){
                if (addressesDTO.getId() == null){
                    Address address = DTOToEntity(addressesDTO);
                    addresses.add(address);
                    continue;
                }
                if (addresses != null){
                    for (Address address: addresses){
                        if(address.getId().equals(addressesDTO.getId())){
                            updateAddress(addressesDTO, address);
                            break;
                        }
                    }
                }
            }
        }

    };

    @Mapping(source = "student.id",target = "id")
    @Mapping(target = "fullName", expression = "java(getFullName(student.getFirstName(),student.getLastName()))")
    @Mapping(source = "student.firstName", target = "firstName")
    @Mapping(source = "student.lastName", target = "lastName")
    @Mapping(source = "student.dept", target = "dept")
    @Mapping(source = "student.year", target = "year")
    @Mapping(target = "ShowStudentAddressesDTO.addresses", expression = "java(getAddress(addresses))")
    ShowStudentAddressesDTO displayStudentAddresses(List<Address> addresses,student student);

    default List<Address> getAddress(List<Address> addresses){
        return addresses;
    }
    default String getFullName(String firstName, String lastName){
        return firstName+" "+lastName;
    }

}
