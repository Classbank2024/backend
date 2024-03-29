package com.ourclassbank.coredomain.support.factory

import com.ourclassbank.coredb.entity.UserClassEntity
import com.ourclassbank.coredb.entity.UserEntity
import com.ourclassbank.coredb.entity.UserRoleEntity
import com.ourclassbank.modeldomain.user.RoleType
import com.ourclassbank.modeldomain.user.User
import com.ourclassbank.modeldomain.user.UserClass
import org.springframework.security.crypto.password.PasswordEncoder

fun User.toEntity(passwordEncoder: PasswordEncoder): UserEntity {
    return UserEntity(
        username = this.username,
        password = passwordEncoder.encode(this.password),
        name = this.name,
        pocketMoneyAccountNo = this.pocketmoneyAccountNo,
        roles = this.roles.map { it.toEntity() },
        userClass = this.userClass.toEntity()
    )
}

fun UserEntity.toModel(): User {
    return User(
        id = this.id,
        username = this.username,
        password = this.password,
        name = this.name,
        pocketmoneyAccountNo = this.pocketMoneyAccountNo,
        roles = this.roles.map { it.toModel() },
        userClass = this.userClass.toModel()
    )
}

fun RoleType.toEntity(): UserRoleEntity {
    return UserRoleEntity(
        role = this
    )
}

fun UserRoleEntity.toModel(): RoleType {
    return this.role
}

fun UserClass.toEntity(): UserClassEntity {
    return UserClassEntity(
        schoolName = this.schoolName,
        grade = this.grade,
        classNumber = this.classNumber,
        attendanceNumber = this.attendanceNumber
    )
}

fun UserClassEntity.toModel(): UserClass {
    return UserClass(
        schoolName = this.schoolName,
        grade = this.grade,
        classNumber = this.classNumber,
        attendanceNumber = this.attendanceNumber
    )
}
