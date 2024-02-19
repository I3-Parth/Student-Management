package com.springboot.studentManagement.services;

import com.springboot.studentManagement.dto.*;
import com.springboot.studentManagement.exceptions.InvalidAddressException;
import com.springboot.studentManagement.mapper.AddressMapper;
import com.springboot.studentManagement.mapper.StudentMapper;
import com.springboot.studentManagement.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.studentManagement.repository.studentRepository;
import com.springboot.studentManagement.model.student;
import com.springboot.studentManagement.exceptions.resourceNotFoundException;
import org.springframework.stereotype.Service;
import com.springboot.studentManagement.repository.addressRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    studentRepository studentRepository;

    @Autowired
    addressRepository addressRepository;

    public List<StudentDTO> getAllStudents(){
        List<student> students=studentRepository.findAll();
        return students.stream().map(studentMapper::modelToDTO).collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id)throws resourceNotFoundException{
        return studentMapper.modelToDTO(studentRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id)));
    }

    public StudentCoursesDTO getCoursesByStudents(Long id)throws resourceNotFoundException{
        return studentMapper.getCoursesByStudent(studentRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id)));
    }

    public Set<CreateStudentDTO> createStudentDTOS(Set<CreateStudentDTO> createStudentDTOS){
        Set<student> students=studentMapper.createDTOToModel(createStudentDTOS);
        this.studentRepository.saveAll(students);
        return studentMapper.createModelToDTO(students);
    }


    public StudentDTO updateStudent(Long id, UpdateStudentDTO updateStudentDTO)throws resourceNotFoundException{
        student student = studentRepository.findById(id).orElseThrow(()-> new resourceNotFoundException(id));
        studentMapper.updateStudent(updateStudentDTO, student);
        student student1 = studentRepository.save(student);
        return studentMapper.modelToDTO(student1);
    }

    public ShowStudentAddressesDTO updateWholeStudent(Long id, UpdateStudentDTO updateStudentDTO)throws resourceNotFoundException, InvalidAddressException{
        student student = studentRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id));
        List<Address> requestAddress = addressMapper.getEntityList(updateStudentDTO.getAddresses());
        int flag = 0;
        if (requestAddress != null){
            for (Address address: requestAddress){
                flag = 0;
                for(Address address1:student.getAddresses()){
                    if (address1.getId().equals(address.getId())) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0 && address.getId() != null){
                    Long errid=address.getId();
                    throw new InvalidAddressException(errid);
                }
            }
        }
        studentMapper.updateStudentInfo1(updateStudentDTO, student);
        student student1 = studentRepository.save(student);
        return studentMapper.displayWholeStudentInfo(student1);
    }

    public ShowStudentAddressesDTO updateStudentAddresses(Long id, List<AddressesDTO> addressesDTOS)throws resourceNotFoundException, InvalidAddressException{
        student student = studentRepository.findById(id).orElseThrow(()-> new resourceNotFoundException(id));
        List<Address> createNew = new ArrayList<>();
        List<Address> updateExisting = new ArrayList<>();
        List<Address> studAddresses = student.getAddresses();
        List<Address> addresses = addressMapper.getEntityList(addressesDTOS);
        int flag=0;
        for(Address a1:addresses){
            flag=0;
            if(a1.getId()==null){
                createNew.add(a1);
                flag=1;
                continue;
            }
            for(Address a2:studAddresses){
                if(a2.getId().equals(a1.getId())){
                    updateExisting.add(a1);
                    flag=1;
                }
            }
            if(flag==0){
                Long errid=a1.getId();
                throw new InvalidAddressException(errid);
            }
        }

        if(!createNew.isEmpty()){
//            for (Address address:createNew){
//                address.setStudent(student);
//            }
            addressRepository.saveAll(createNew);

        }

        if (!updateExisting.isEmpty()){
            List<AddressesDTO> addressesDTOS1 =addressMapper.getDTOList(updateExisting);
            addressMapper.updateAddresses(addressesDTOS1, updateExisting);
//            for (Address address:updateExisting){
//                address.setStudent(student);
//            }
            addressRepository.saveAll(updateExisting);

        }

        List<Address> merged=createNew;
        merged.addAll(updateExisting);

        return addressMapper.displayStudentAddresses(merged, student);
    }
}
