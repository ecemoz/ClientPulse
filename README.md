# ClientPulse Projesi

**ClientPulse**, müşteri davranışlarını analiz eden ve kişiselleştirilmiş müdahale önerileri sunan bir kurumsal yazılım projesidir. Bu proje, banka ve finansal kurumlar için müşteri profillemesi, skorlar, risk analizi ve AI destekli öneriler sunmaktadır.

## Proje İçeriği

Bu repo, aşağıdaki işlevleri yerine getiren bir müşteri profilleme ve yönetim sistemini içerir:

- **Kullanıcı Yönetimi**: Kullanıcı oluşturma, güncelleme ve silme işlemleri.
- **Müşteri Profilleme**: Her kullanıcıya ait bir müşteri profili oluşturulması, yönetilmesi ve güncellenmesi.
- **Skor Hesaplama**: Churn, loyalty skorları hesaplanması ve segmentasyon.
- **AI Tabanlı Öneriler**: Kullanıcılar için AI tabanlı öneri sistemleri.
- **Veritabanı Yönetimi**: PostgreSQL veya MongoDB kullanarak veritabanı yönetimi.

## Başlangıç

Bu projeyi çalıştırabilmek için aşağıdaki adımları takip edebilirsiniz.

### Gereksinimler

- Java 17 veya üzeri
- Spring Boot 3.4.x
- PostgreSQL 
- IntelliJ IDEA veya başka bir Java IDE
- Maven veya Gradle
- Test için Swagger

### Projeyi Çalıştırma

1. **Proje Kopyalama**:
    ```bash
    git clone https://github.com/yourusername/clientpulse.git
    ```

2. **Veritabanı Yapılandırması**:
    PostgreSQL veya MongoDB'yi kurun ve yapılandırın.
    Veritabanı bağlantı bilgilerini `application.properties` dosyasına ekleyin:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/clientpulse
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Uygulama Çalıştırma**:
    Maven veya Gradle kullanarak uygulamayı çalıştırın:
    ```bash
    mvn spring-boot:run
    ```
    veya
    ```bash
    ./gradlew bootRun
    ```

### API Endpoints

#### Kullanıcı Endpoints

- **POST /users**: Yeni kullanıcı ve müşteri profili oluşturur.
  ```json
  {
    "firstname": "John",
    "lastname": "Doe",
    "email": "johndoe@example.com",
    "username": "johndoe123",
    "password": "securepassword",
    "phone": "1234567890",
    "role": "ADMIN"
  }
  ```

- **GET /users/{id}**: Belirli bir kullanıcıyı ve profil bilgilerini getirir.

- **PUT /users/{id}**: Kullanıcı ve profil bilgilerini günceller.
  
- **DELETE /users/{id}**: Kullanıcı ve profilini siler.

#### Müşteri Profil Endpoints

- **GET /customer/{userId}/profile**: Kullanıcıya ait müşteri profilini alır.
  
- **POST /customer/{userId}/profile**: Kullanıcı için yeni bir müşteri profili oluşturur.

- **GET /customers**: Tüm müşteri profillerini listelemek için kullanılır.

#### Admin Endpoints

- **GET /admin/customers**: Admin paneli için tüm müşteri profillerini listeler.

### Servisler

#### 1. **UserService**
Kullanıcılar için temel CRUD işlemlerini yapar. 

```java
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstname(updatedUser.getFirstname());
            user.setLastname(updatedUser.getLastname());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
```

#### 2. **CustomerProfileService**
Kullanıcıya ait müşteri profilini oluşturma, listeleme ve güncelleme işlemleri yapar.

```java
@Service
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {
    
    private final CustomerProfileRepository customerProfileRepository;
    private final UserRepository userRepository;

    @Override
    public CustomerProfile createProfileForUser(User user) {
        if (customerProfileRepository.findByUser(user).isPresent()) {
            return customerProfileRepository.findByUser(user).get();
        }

        CustomerProfile profile = new CustomerProfile();
        profile.setUser(user);
        profile.setChurnScore(0.0);
        profile.setLoyaltyScore(0.0);
        profile.setSegment("NEW");
        profile.setLastActivity(LocalDateTime.now());
        profile.setAiRecommendations("Hoş geldiniz! Henüz öneriniz yok.");

        return customerProfileRepository.save(profile);
    }

    @Override
    public List<CustomerProfile> getAllProfiles() {
        return customerProfileRepository.findAll();
    }

    @Override
    public CustomerProfile getProfileByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return customerProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer profile not found"));
    }
}
```

### Veritabanı Yapısı

**User** ve **CustomerProfile** modelleri arasında bir ilişki vardır. Kullanıcı her zaman bir müşteri profiline sahip olmalıdır.

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String phone;
    
    @OneToOne(mappedBy = "user")
    private CustomerProfile customerProfile;
    
    // Getter ve Setter'lar
}
```

```java
@Entity
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private Double churnScore;
    private Double loyaltyScore;
    private String segment;
    private LocalDateTime lastActivity;
    private String aiRecommendations;
    
    // Getter ve Setter'lar
}
```

### Test Senaryoları

#### Kullanıcı Profili Oluşturma

1. Yeni bir kullanıcı kaydetmek için `POST /users` endpoint'ini kullanın.
2. Kullanıcıya ait profil verilerini `POST /customer/{userId}/profile` ile oluşturun.
3. Kullanıcı profiline ait verileri `GET /customer/{userId}/profile` ile sorgulayın.

#### Müşteri Skorları ve Öneriler

1. Kullanıcı profili oluşturulduktan sonra, AI tabanlı öneri sistemi çalıştırarak müşteri önerilerini ekleyin.
2. Kullanıcıya ait churn ve loyalty skorlarını güncelleyerek müşteri segmentini belirleyin.

---
Tüm hakları saklıdır...
