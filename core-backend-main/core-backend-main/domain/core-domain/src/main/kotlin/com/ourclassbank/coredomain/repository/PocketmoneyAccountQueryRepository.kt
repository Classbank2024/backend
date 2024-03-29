package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.PocketmoneyAccountHistoryEntityJpaDao
import com.ourclassbank.coredb.dao.PocketmoneyAccountHistoryEntityQuerydslDao
import com.ourclassbank.coredb.dao.UserEntityJpaDao
import com.ourclassbank.coredomain.support.factory.toModel
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PocketmoneyAccountQueryRepository(
    private val pocketmoneyAccountHistoryEntityJpaDao: PocketmoneyAccountHistoryEntityJpaDao,
    private val pocketmoneyAccountHistoryEntityQuerydslDao: PocketmoneyAccountHistoryEntityQuerydslDao,
    private val userEntityJpaDao: UserEntityJpaDao
) {
    fun findAllByAccountNo(
        accountNo: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return pocketmoneyAccountHistoryEntityJpaDao.findAllByAccountNoAndCreatedAtBetweenOrderByCreatedAtDesc(
            accountNo,
            fromAt,
            toAt
        ).map {
            val ownerUserEntity = userEntityJpaDao.findByPocketMoneyAccountNo(it.accountNo)
                ?: throw IllegalArgumentException("존재하지 않는 회원")
            val createUserEntity =
                userEntityJpaDao.findByUsername(it.createdBy!!) ?: throw IllegalArgumentException("존재하지 않는 회원")
            it.toModel(ownerUserEntity, createUserEntity)
        }
    }

    fun findAllByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return pocketmoneyAccountHistoryEntityJpaDao.findAllByCreatedByAndCreatedAtBetweenOrderByCreatedAtDesc(
            createdBy,
            fromAt,
            toAt
        ).map {
            val ownerUserEntity = userEntityJpaDao.findByPocketMoneyAccountNo(it.accountNo)
                ?: throw IllegalArgumentException("존재하지 않는 회원")
            val createUserEntity =
                userEntityJpaDao.findByUsername(it.createdBy!!) ?: throw IllegalArgumentException("존재하지 않는 회원")
            it.toModel(ownerUserEntity, createUserEntity)
        }
    }

    fun findAllHistoryBySameClass(
        username: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return pocketmoneyAccountHistoryEntityQuerydslDao.findAllHistoryBySameClass(username, fromAt, toAt).map {
            it.toModel(
                ownerUserEntity = userEntityJpaDao.findByPocketMoneyAccountNo(it.accountNo)
                    ?: throw IllegalArgumentException("존재하지 않는 회원"),
                executeUserEntity = userEntityJpaDao.findByUsername(it.createdBy!!)
                    ?: throw IllegalArgumentException("존재하지 않는 회원")
            )
        }
    }

    fun count(): Long {
        return pocketmoneyAccountHistoryEntityJpaDao.count()
    }
}
